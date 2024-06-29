; ModuleID = 'Operate_arrays_through_loops.c'
source_filename = "Operate_arrays_through_loops.c"
target datalayout = "E-m:m-p:32:32-i8:8:32-i16:16:32-i64:64-n32-S64"
target triple = "mips"

; Function Attrs: noinline nounwind optnone
define dso_local i32 @main() local_unnamed_addr #0 {
  %1 = alloca i32, align 4
  %2 = alloca i32, align 4
  %3 = alloca [10 x i32], align 4
  store i32 0, ptr %1, align 4
  call void @llvm.lifetime.start.p0(i64 4, ptr %2) #2
  call void @llvm.lifetime.start.p0(i64 40, ptr %3) #2
  store i32 10, ptr %2, align 4, !tbaa !3
  br label %4

4:                                                ; preds = %7, %0
  %5 = load i32, ptr %2, align 4, !tbaa !3
  %6 = icmp sgt i32 %5, 0
  br i1 %6, label %7, label %12

7:                                                ; preds = %4
  %8 = load i32, ptr %2, align 4, !tbaa !3
  %9 = getelementptr inbounds [10 x i32], ptr %3, i32 0, i32 %8
  store i32 0, ptr %9, align 4, !tbaa !3
  %10 = load i32, ptr %2, align 4, !tbaa !3
  %11 = add nsw i32 %10, -1
  store i32 %11, ptr %2, align 4, !tbaa !3
  br label %4, !llvm.loop !7

12:                                               ; preds = %4
  store i32 10, ptr %2, align 4, !tbaa !3
  br label %13

13:                                               ; preds = %16, %12
  %14 = load i32, ptr %2, align 4, !tbaa !3
  %15 = icmp sgt i32 %14, 0
  br i1 %15, label %16, label %23

16:                                               ; preds = %13
  %17 = load i32, ptr %2, align 4, !tbaa !3
  %18 = getelementptr inbounds [10 x i32], ptr %3, i32 0, i32 %17
  %19 = load i32, ptr %18, align 4, !tbaa !3
  %20 = add nsw i32 %19, 1
  store i32 %20, ptr %18, align 4, !tbaa !3
  %21 = load i32, ptr %2, align 4, !tbaa !3
  %22 = add nsw i32 %21, -1
  store i32 %22, ptr %2, align 4, !tbaa !3
  br label %13, !llvm.loop !9

23:                                               ; preds = %13
  call void @llvm.lifetime.end.p0(i64 40, ptr %3) #2
  call void @llvm.lifetime.end.p0(i64 4, ptr %2) #2
  ret i32 0
}

; Function Attrs: mustprogress nocallback nofree nosync nounwind willreturn memory(argmem: readwrite)
declare void @llvm.lifetime.start.p0(i64 immarg, ptr nocapture) #1

; Function Attrs: mustprogress nocallback nofree nosync nounwind willreturn memory(argmem: readwrite)
declare void @llvm.lifetime.end.p0(i64 immarg, ptr nocapture) #1

attributes #0 = { noinline nounwind optnone "frame-pointer"="all" "no-trapping-math"="true" "stack-protector-buffer-size"="8" "target-cpu"="mips32r2" "target-features"="+fpxx,+mips32r2,+nooddspreg,-noabicalls" }
attributes #1 = { mustprogress nocallback nofree nosync nounwind willreturn memory(argmem: readwrite) }
attributes #2 = { nounwind }

!llvm.module.flags = !{!0, !1}
!llvm.ident = !{!2}

!0 = !{i32 1, !"wchar_size", i32 4}
!1 = !{i32 7, !"frame-pointer", i32 2}
!2 = !{!"clang version 17.0.6"}
!3 = !{!4, !4, i64 0}
!4 = !{!"int", !5, i64 0}
!5 = !{!"omnipotent char", !6, i64 0}
!6 = !{!"Simple C/C++ TBAA"}
!7 = distinct !{!7, !8}
!8 = !{!"llvm.loop.mustprogress"}
!9 = distinct !{!9, !8}
