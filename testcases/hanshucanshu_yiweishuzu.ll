; ModuleID = 'hanshucanshu_yiweishuzu.c'
source_filename = "hanshucanshu_yiweishuzu.c"
target datalayout = "E-m:m-p:32:32-i8:8:32-i16:16:32-i64:64-n32-S64"
target triple = "mips"

; Function Attrs: mustprogress nofree norecurse nosync nounwind willreturn memory(argmem: read)
define dso_local i32 @head(ptr nocapture noundef readonly %0) local_unnamed_addr #0 {
  %2 = load i32, ptr %0, align 4, !tbaa !3
  ret i32 %2
}

; Function Attrs: noinline nounwind optnone
define dso_local i32 @main() local_unnamed_addr #1 {
  %1 = alloca i32, align 4
  %2 = alloca [2 x i32], align 4
  %3 = alloca i32, align 4
  store i32 0, ptr %1, align 4
  call void @llvm.lifetime.start.p0(i64 8, ptr %2) #3
  call void @llvm.lifetime.start.p0(i64 4, ptr %3) #3
  %4 = getelementptr inbounds [2 x i32], ptr %2, i32 0, i32 0
  store i32 1, ptr %4, align 4, !tbaa !3
  %5 = getelementptr inbounds [2 x i32], ptr %2, i32 0, i32 1
  store i32 2, ptr %5, align 4, !tbaa !3
  %6 = getelementptr inbounds [2 x i32], ptr %2, i32 0, i32 0
  %7 = call i32 @head(ptr noundef %6)
  store i32 %7, ptr %3, align 4, !tbaa !3
  call void @llvm.lifetime.end.p0(i64 4, ptr %3) #3
  call void @llvm.lifetime.end.p0(i64 8, ptr %2) #3
  ret i32 0
}

; Function Attrs: mustprogress nocallback nofree nosync nounwind willreturn memory(argmem: readwrite)
declare void @llvm.lifetime.start.p0(i64 immarg, ptr nocapture) #2

; Function Attrs: mustprogress nocallback nofree nosync nounwind willreturn memory(argmem: readwrite)
declare void @llvm.lifetime.end.p0(i64 immarg, ptr nocapture) #2

attributes #0 = { mustprogress nofree norecurse nosync nounwind willreturn memory(argmem: read) "frame-pointer"="all" "no-trapping-math"="true" "stack-protector-buffer-size"="8" "target-cpu"="mips32r2" "target-features"="+fpxx,+mips32r2,+nooddspreg,-noabicalls" }
attributes #1 = { noinline nounwind optnone "frame-pointer"="all" "no-trapping-math"="true" "stack-protector-buffer-size"="8" "target-cpu"="mips32r2" "target-features"="+fpxx,+mips32r2,+nooddspreg,-noabicalls" }
attributes #2 = { mustprogress nocallback nofree nosync nounwind willreturn memory(argmem: readwrite) }
attributes #3 = { nounwind }

!llvm.module.flags = !{!0, !1}
!llvm.ident = !{!2}

!0 = !{i32 1, !"wchar_size", i32 4}
!1 = !{i32 7, !"frame-pointer", i32 2}
!2 = !{!"clang version 17.0.6"}
!3 = !{!4, !4, i64 0}
!4 = !{!"int", !5, i64 0}
!5 = !{!"omnipotent char", !6, i64 0}
!6 = !{!"Simple C/C++ TBAA"}
