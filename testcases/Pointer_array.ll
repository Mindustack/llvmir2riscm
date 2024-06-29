; ModuleID = 'Pointer_array.c'
source_filename = "Pointer_array.c"
target datalayout = "E-m:m-p:32:32-i8:8:32-i16:16:32-i64:64-n32-S64"
target triple = "mips"

; Function Attrs: noinline nounwind optnone
define dso_local i32 @main() local_unnamed_addr #0 {
  %1 = alloca i32, align 4
  %2 = alloca [5 x i32], align 4
  %3 = alloca [5 x ptr], align 4
  %4 = alloca i32, align 4
  %5 = alloca i32, align 4
  store i32 0, ptr %1, align 4
  call void @llvm.lifetime.start.p0(i64 20, ptr %2) #2
  call void @llvm.lifetime.start.p0(i64 20, ptr %3) #2
  call void @llvm.lifetime.start.p0(i64 4, ptr %4) #2
  call void @llvm.lifetime.start.p0(i64 4, ptr %5) #2
  %6 = getelementptr inbounds [5 x i32], ptr %2, i32 0, i32 0
  store i32 1, ptr %6, align 4, !tbaa !3
  %7 = getelementptr inbounds [5 x i32], ptr %2, i32 0, i32 1
  store i32 2, ptr %7, align 4, !tbaa !3
  %8 = getelementptr inbounds [5 x i32], ptr %2, i32 0, i32 2
  store i32 3, ptr %8, align 4, !tbaa !3
  %9 = getelementptr inbounds [5 x i32], ptr %2, i32 0, i32 3
  store i32 4, ptr %9, align 4, !tbaa !3
  %10 = getelementptr inbounds [5 x i32], ptr %2, i32 0, i32 4
  store i32 5, ptr %10, align 4, !tbaa !3
  store i32 0, ptr %5, align 4, !tbaa !3
  store i32 0, ptr %4, align 4, !tbaa !3
  br label %11

11:                                               ; preds = %14, %0
  %12 = load i32, ptr %4, align 4, !tbaa !3
  %13 = icmp slt i32 %12, 5
  br i1 %13, label %14, label %21

14:                                               ; preds = %11
  %15 = load i32, ptr %4, align 4, !tbaa !3
  %16 = getelementptr inbounds [5 x i32], ptr %2, i32 0, i32 %15
  %17 = load i32, ptr %4, align 4, !tbaa !3
  %18 = getelementptr inbounds [5 x ptr], ptr %3, i32 0, i32 %17
  store ptr %16, ptr %18, align 4, !tbaa !7
  %19 = load i32, ptr %4, align 4, !tbaa !3
  %20 = add nsw i32 %19, 1
  store i32 %20, ptr %4, align 4, !tbaa !3
  br label %11, !llvm.loop !9

21:                                               ; preds = %11
  %22 = getelementptr inbounds [5 x ptr], ptr %3, i32 0, i32 1
  %23 = load ptr, ptr %22, align 4, !tbaa !7
  %24 = load i32, ptr %23, align 4, !tbaa !3
  %25 = icmp eq i32 %24, 2
  br i1 %25, label %26, label %27

26:                                               ; preds = %21
  store i32 0, ptr %5, align 4, !tbaa !3
  br label %28

27:                                               ; preds = %21
  store i32 -1, ptr %5, align 4, !tbaa !3
  br label %28

28:                                               ; preds = %27, %26
  %29 = load i32, ptr %5, align 4, !tbaa !3
  call void @llvm.lifetime.end.p0(i64 4, ptr %5) #2
  call void @llvm.lifetime.end.p0(i64 4, ptr %4) #2
  call void @llvm.lifetime.end.p0(i64 20, ptr %3) #2
  call void @llvm.lifetime.end.p0(i64 20, ptr %2) #2
  ret i32 %29
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
!7 = !{!8, !8, i64 0}
!8 = !{!"any pointer", !5, i64 0}
!9 = distinct !{!9, !10}
!10 = !{!"llvm.loop.mustprogress"}
